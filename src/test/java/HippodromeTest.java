import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.*;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    Hippodrome hippodrome;
    List<Horse> horses = new ArrayList<>();

    @Test
    void testHippodromeNullList(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->
                hippodrome = new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void testHippodromeEmptyList(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->
                hippodrome = new Hippodrome(horses));
        Assertions.assertEquals("Horses cannot be empty.", thrown.getMessage());
    }

    @Test
    void testGetHorses() {

        Random randomSpeed = new Random();
        for (int i = 0; i < 30; i++){
            horses.add(new Horse("Horse" + i, randomSpeed.nextDouble()));
        }

        hippodrome = new Hippodrome(horses);
        Assertions.assertTrue(Arrays.deepEquals(horses.toArray(), hippodrome.getHorses().toArray()));
    }

    @Test
    void testMove(){
        //arrange
        List<Horse> horsesMocks = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            horsesMocks.add(mock(Horse.class));
        }
        //act
        new Hippodrome(horsesMocks).move();
        //assert
        for (Horse horse : horsesMocks){
            verify(horse, atLeast(1)).move();
        }
    }

    @Test
    void testGetWinner(){
        //arrange
        for (int i = 0; i < 50; i++){
            horses.add(new Horse("Horse" + 1, 2.0, i));
        }
        //act
        hippodrome = new Hippodrome(horses);
        //assert
        Assertions.assertSame(horses.get(49), hippodrome.getWinner());
    }

}
