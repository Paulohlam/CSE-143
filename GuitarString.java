// Paul Lam
// Section AG, Neel jog
// A2 GuitarString
// GuitarString creates the virbation of a guitar based on given frequency.

import java.util.*;

public class GuitarString {

   private Queue < Double > ringBuffer;
   private double ringCap;
   public static final double ENERGY_DECAY_FACTOR = 0.996;

   // Purpose: Takes in given frequency and creates a ringBuffer with the
   // required size capacity. Represents string at rest.
   // Pre: Frequency must be greater than or equal to 0. Resulting ringBuffer size must be
   // greater than 2. If condtions are violated (throws an IllegalArgumentException).
   // Post: Creates a ringBuffer and ringCapacity at rest.
   public GuitarString(double frequency) {
      if (frequency <= 0) {
         throw new IllegalArgumentException();
      }
      ringCap = Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (ringCap < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList < > ();
      for (int i = 0; i < ringCap; i++) {
         ringBuffer.add(0.0);
      }
   }

   // Purpose: Creates a guitar string that represents a specific set of
   // frequencys. This is used for testing purposes.
   // Pre: Count of frequency must be greater than 2. If violated
   // (throws an IllegalArgumentException).
   // Post: Creates a guitar string that represents a specific set of frequencys.
   public GuitarString(double[] init) {
      ringCap = init.length;
      if (ringCap < 2.0) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList < > ();
      for (int i = 0; i < ringCap; i++) {
         ringBuffer.add(init[i]);
      }
   }

   // Purpose: Replaces old set of frequency with new random frequency.
   // Pre: No pre condition.
   // Post: Generates new set of random frequency and replaces old.
   public void pluck() {
      for (int i = 0; i < ringCap; i++) {
         double randomNum = Math.random() - 0.5;
         ringBuffer.add(randomNum);
         ringBuffer.remove();
      }
   }

   // Purpose: Takes first 2 frequency and calculates the average frequency of the two.
   // Then removes the first frequency and adds the average to the end of set.
   // Pre: No pre condition.
   // Post: Takes the result of the first two frequency using Karplus-Strong algorithm.
   // Removes the first frequency and adds the result to the end of set.
   public void tic() {
      double firstSample = ringBuffer.remove();
      double secondSample = ringBuffer.peek();
      double newSample = ((secondSample + firstSample) / 2) * ENERGY_DECAY_FACTOR;
      ringBuffer.add(newSample);
   }

   // Purpose: Gives you the first frequency in the set.
   // Pre: No pre conditions.
   // Post: Returns first frequency in the set.
   public double sample() {
      return ringBuffer.peek();
   }
}