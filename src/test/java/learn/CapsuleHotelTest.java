package learn;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CapsuleHotelTest {
    public InputStream originalSystemIn;
    public Scanner scanner;
    CapsuleHotel capsuleHotel;
    public final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    public final PrintStream originalSystemOut = System.out;

    @BeforeEach
    public void setUp() {
        // Save the original System.in to restore it later
        originalSystemIn = System.in;
        capsuleHotel = new CapsuleHotel(20);
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        // Restore the original System.in
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }


    //should set number of capsules on startup

    @Test
    public void shouldRunTestsTest(){
        String expected = "working";
        assertEquals(expected, "working");
    }

    // check-in
    @Test
    public void shouldCheckInGuestToNullCapsuleTest(){
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);
        assertEquals("John", capsuleHotel.capsules[0]);
        assertTrue(result);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Success :)\n" +
                "John is booked in capsule #1."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

    }

    @Test
    public void shouldNotCheckGuestInToOccupiedCapsuleTest(){
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);
        assertEquals("John", capsuleHotel.capsules[0]);
        assertTrue(result);

        input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        result = capsuleHotel.handleCheckIn(scanner, "Mary");
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Error :(\n" +
                "Capsule #1 is occupied."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
        assertNotNull(capsuleHotel.capsules[0]);
        assertEquals("John", capsuleHotel.capsules[0]);
        assertFalse(result);
    }

    @Test
    public void shouldShowErrorMessageWhenCheckingInToNonExistingRoomTest(){
        String input = "50\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input
        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        // Get the captured output
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Error :(\n" +
                "Capsule #50 does not exist."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
        assertFalse(result);

    }

    //check out
    @Test
    public void shouldCheckGuestOutOfOccupiedRoomTest(){
        //check a guest in
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);

        // check a guest out
        input = "1\n";  // Input to simulate user typing "1" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input
        result = capsuleHotel.handleCheckOut(scanner);
        assertNull(capsuleHotel.capsules[0]);
        assertTrue(result);
        // Get the captured output
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Success :)\n" +
                "John checked out from capsule #1."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

    }

    @Test
    public void shouldNotCheckGuestOutOfUnoccupiedRoomTest(){
        //check a guest in
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);


        // check a guest out
        input = "5\n";  // Input to simulate user typing "5" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input
        result = capsuleHotel.handleCheckOut(scanner);
        assertNull(capsuleHotel.capsules[4]);
        assertFalse(result);
        // Get the captured output
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Error :(\n" +
                "Capsule #5 is unoccupied."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
    }

    @Test
    public void shouldNotAskForCapsuleIfNoGuestsCheckedInTest(){
        // check a guest out
        String input = "5\n";  // Input to simulate user typing "5" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input
        boolean result = capsuleHotel.handleCheckOut(scanner);
        assertFalse(result);
        // Get the captured output
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Sorry... check out is only available if there's at least one guest."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
    }

    @Test
    public void shouldShowErrorMessageWhenCheckingOutNonExistingRoomTest(){
        //check a guest in
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);

        // check a guest out
        input = "21\n";  // Input to simulate user typing "5" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input
        result = capsuleHotel.handleCheckOut(scanner);
        assertFalse(result);
        // Get the captured output
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Error :(\n" +
                "Capsule #21 does not exist."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
    }

    //view guests
    @Test
    public void shouldViewElevenAtATimeTest(){
        //check in at least 1 guest
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);
        assertEquals("John", capsuleHotel.capsules[0]);
        assertTrue(result);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Success :)\n" +
                "John is booked in capsule #1."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

        // view capsule 10
        input = "9\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream
        scanner = new Scanner(System.in);
        capsuleHotel.viewGuests(scanner);
        capturedOutput = outputStream.toString().trim();
        expectedOutput = "Capsule: Guest\n" +
                "4: [unoccupied]\n" +
                "5: [unoccupied]\n" +
                "6: [unoccupied]\n" +
                "7: [unoccupied]\n" +
                "8: [unoccupied]\n" +
                "9: [unoccupied]\n" +
                "10: [unoccupied]\n" +
                "11: [unoccupied]\n" +
                "12: [unoccupied]\n" +
                "13: [unoccupied]\n" +
                "14: [unoccupied]"; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

    }

    @Test
    public void shouldHandleLowerBoundsTest(){
        //check in at least 1 guest
        String input = "1\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[0]);
        assertEquals("John", capsuleHotel.capsules[0]);
        assertTrue(result);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Success :)\n" +
                "John is booked in capsule #1."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

        // view capsule 10
        input = "1\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream
        scanner = new Scanner(System.in);
        capsuleHotel.viewGuests(scanner);
        capturedOutput = outputStream.toString().trim();
        expectedOutput = "Capsule: Guest\n" +
                "1: John\n" +
                "2: [unoccupied]\n" +
                "3: [unoccupied]\n" +
                "4: [unoccupied]\n" +
                "5: [unoccupied]\n" +
                "6: [unoccupied]\n" +
                "7: [unoccupied]\n" +
                "8: [unoccupied]\n" +
                "9: [unoccupied]\n" +
                "10: [unoccupied]\n" +
                "11: [unoccupied]"; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
    }

    @Test
    public void shouldHandleUpperBoundsTest(){
        //check in at least 1 guest
        String input = "10\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[9]);
        assertEquals("John", capsuleHotel.capsules[9]);
        assertTrue(result);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Success :)\n" +
                "John is booked in capsule #10."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

        // view capsule 10
        input = "20\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream
        scanner = new Scanner(System.in);
        capsuleHotel.viewGuests(scanner);
        capturedOutput = outputStream.toString().trim();
        expectedOutput = "Capsule: Guest\n" +
                "10: John\n" +
                "11: [unoccupied]\n" +
                "12: [unoccupied]\n" +
                "13: [unoccupied]\n" +
                "14: [unoccupied]\n" +
                "15: [unoccupied]\n" +
                "16: [unoccupied]\n" +
                "17: [unoccupied]\n" +
                "18: [unoccupied]\n" +
                "19: [unoccupied]\n" +
                "20: [unoccupied]"; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));
    }

    @Test
    public void shouldHandleOutOfBoundsTest(){
        //check in at least 1 guest
        String input = "10\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.handleCheckIn(scanner, "John");
        assertNotNull(capsuleHotel.capsules[9]);
        assertEquals("John", capsuleHotel.capsules[9]);
        assertTrue(result);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Success :)\n" +
                "John is booked in capsule #10."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

        // view capsule 10
        input = "22\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream
        scanner = new Scanner(System.in);
        capsuleHotel.viewGuests(scanner);
        capturedOutput = outputStream.toString().trim();
        expectedOutput = "Error :(\n" +
                "Capsule #22 does not exist."; // Replace with your expected output
        assertTrue(capturedOutput.contains(expectedOutput));

    }

    //exit program
    @Test
    public void shouldConfirmExitTest(){
        String input = "y\n";  // Input to simulate user typing "42" and pressing Enter
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        boolean result = capsuleHotel.confirmExit(scanner);
        assertTrue(result);

        input = "Y\n";  // Input to simulate user typing "42" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        result = capsuleHotel.confirmExit(scanner);
        assertTrue(result);

        input = "n\n";  // Input to simulate user typing "42" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        result = capsuleHotel.confirmExit(scanner);
        assertFalse(result);

        input = "h\n";  // Input to simulate user typing "42" and pressing Enter
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);  // Set the simulated input stream

        scanner = new Scanner(System.in);  // Create a scanner to read from the simulated input

        result = capsuleHotel.confirmExit(scanner);
        assertFalse(result);
    }
}