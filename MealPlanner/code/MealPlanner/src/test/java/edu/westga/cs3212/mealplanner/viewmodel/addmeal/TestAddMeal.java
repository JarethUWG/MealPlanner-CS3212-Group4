package edu.westga.cs3212.mealplanner.viewmodel.addmeal;

import com.google.gson.Gson;
import edu.westga.cs3212.mealplanner.model.*;
import edu.westga.cs3212.mealplanner.viewmodel.AddMealViewModel;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.zeromq.ZMQ;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestAddMeal {
    private static ZMQ.Context context;
    private static ZMQ.Socket socket;
    private static Thread thread;
    private static MockServer mockServer = new MockServer();
    private AddMealViewModel viewModel;

    @BeforeAll
    static void setUpSocket() {
        mockServer = new MockServer();
        thread = new Thread(mockServer);
        thread.start();
        context = ZMQ.context(1);
        socket = context.socket(ZMQ.REQ);
        socket.connect("tcp://127.0.0.1:5555");
    }

    @AfterAll
    static void tearDownSocket() {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("reqtype", "exit");
        Messenger.request(message);
        socket.close();
        context.term();
    }

    @BeforeEach
    void setUpSystem() {
        this.viewModel = new AddMealViewModel();
        SystemInfo.setLoggedInUser(new User("Test User", "Test Password"));
    }

    @Test
    void testCannotAddWithNoIngredients() {
        assertNull(this.viewModel.addMeal("a meal", ""));
    }

    @Test
    void testAddingOneMeal() {
        LocalDate time = LocalDate.now();
        this.viewModel.addIngredient(new Ingredient("test", 99));
        this.viewModel.setDate(time);
        var mealAdded = this.viewModel.addMeal("meal", "");
        Meal replyMeal = this.getReplyMeal();
        var mealMade = new ArrayList<Meal>(Collections.singletonList(mealAdded));
        var storedMeals = SystemInfo.getLoggedInUser().getUserPlanner().getPlannedMeals();
        assertEquals(mealAdded.getName(), replyMeal.getName());
        assertEquals(mealAdded.getTotalCalories(), replyMeal.getTotalCalories());
        assertEquals(mealAdded.getDescription(), replyMeal.getDescription());
        assertEquals(mealAdded.getIngredients(), replyMeal.getIngredients());
        assertEquals(mealMade, storedMeals);
    }

    @Test
    void testAddingThreeMeals() {
        LocalDate time = LocalDate.now();
        this.viewModel.addIngredient(new Ingredient("ing 1", 99));
        this.viewModel.setDate(time);
        var mealA = this.viewModel.addMeal("A", "");
        Meal replyMealA = this.getReplyMeal();
        this.viewModel.addIngredient(new Ingredient("ing 2", 8881));
        this.viewModel.addIngredient(new Ingredient("ing 3", 13));
        this.viewModel.addIngredient(new Ingredient("ing 4", 554));
        this.viewModel.setDate(time);
        var mealB = this.viewModel.addMeal("B", "");
        Meal replyMealB = this.getReplyMeal();
        this.viewModel.addIngredient(new Ingredient("ing 5", 12));
        this.viewModel.addIngredient(new Ingredient("ing 6", 37));
        this.viewModel.setDate(time);
        var mealC = this.viewModel.addMeal("C", "");
        Meal replyMealC = this.getReplyMeal();
        var mealsMade = new ArrayList<Meal>(Arrays.asList(mealA, mealB, mealC));
        var storedMeals = SystemInfo.getLoggedInUser().getUserPlanner().getPlannedMeals();
        assertEquals(mealsMade, storedMeals);
        assertEquals(mealA.getName(), replyMealA.getName());
        assertEquals(mealA.getTotalCalories(), replyMealA.getTotalCalories());
        assertEquals(mealA.getDescription(), replyMealA.getDescription());
        assertEquals(mealA.getIngredients(), replyMealA.getIngredients());
        assertEquals(mealB.getName(), replyMealB.getName());
        assertEquals(mealB.getTotalCalories(), replyMealB.getTotalCalories());
        assertEquals(mealB.getDescription(), replyMealB.getDescription());
        assertEquals(mealB.getIngredients(), replyMealB.getIngredients());
        assertEquals(mealC.getName(), replyMealC.getName());
        assertEquals(mealC.getTotalCalories(), replyMealC.getTotalCalories());
        assertEquals(mealC.getDescription(), replyMealC.getDescription());
        assertEquals(mealC.getIngredients(), replyMealC.getIngredients());
    }

    private Meal getReplyMeal() {
        byte[] reply = mockServer.getCurrentReply();
        String message = new String(reply, ZMQ.CHARSET);
        JSONObject jsonConvert = new JSONObject(message);
        Map<String, Object> processedMessage;
        processedMessage = jsonConvert.toMap();
        String rawMeal = (String) processedMessage.get("meal");
        Gson gson = new Gson();
        return gson.fromJson(rawMeal, Meal.class);
    }
}
