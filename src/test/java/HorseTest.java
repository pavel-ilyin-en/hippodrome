import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class HorseTest {

    Horse horse;

    @Test
    void testNullNameException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->
                horse = new Horse(null, 2.4));
        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings= {" ", "", "   ", "\t\t", "\n\n\n"})
    void testBlankNameException(String name) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->
                horse = new Horse(name, 2.4));
        assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    void testNegativeSpeedException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->
                horse = new Horse("Biscuit", -2.0));
        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }

    @Test
    void testNegativeDistanceException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, ()->
                horse = new Horse("Biscuit", 2.4, -1));
        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }

    @Test
    void testGetName(){
        horse = new Horse("Biscuit", 1.7);
        assertEquals("Biscuit", horse.getName());
    }

    @Test
    void testGetSpeed(){
        horse = new Horse("Biscuit", 1.7);
        assertEquals(1.7, horse.getSpeed());
    }

    @Test
    void testGetAssignedDistance(){
        horse = new Horse("Biscuit", 1.7, 0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void testGetDefaultDistance(){
        horse = new Horse("Biscuit", 1.7);
        assertEquals(0, horse.getDistance());
    }

    @Mock
    Horse horseMock;

    @Test
    void testMove(){
        horseMock.move();
        verify(horseMock, times(1)).move();
    }


    @Test
    void testGetRandomDouble(){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("qwerty", 1.0, 1.0).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.5, 0.7, 0.8})
    void testMoveDistance(double arg){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("Horse", 1.0);

            horse.move();

            assertEquals(horse.getSpeed()*arg, horse.getDistance());
        }
    }
}
