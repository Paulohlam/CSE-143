// Paul Lam
// Section AG, Neel jog
// A2 GuitarString
// Guitar37 creates and stores a guitar with 37 different strings.

import java.util.*;

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; // keyboard layout

   public static final int NUM_STRINGS = 37;
   private GuitarString[] guitarStrings;
   private int ticCalled;

   // Purpose: Creates a set of 37 strings.
   // Pre: No pre condition.
   // Post: Creates a set of 37 guitar strings.
   public Guitar37() {
   guitarStrings = new GuitarString[NUM_STRINGS];
      for (int i = 0; i < NUM_STRINGS; i++) {
         double frequency = (440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
         guitarStrings[i] = new GuitarString(frequency);
      }
   }

   // Purpose: Plays the note depending on the pitch.
   // Pre: Pitch must be less than 12 and greater than -24.
   // If Pitch violates condtions, it is ignored.
   // Post: Plays specific note based on the pitch.
   public void playNote(int pitch) {
      if (-24 <= pitch && pitch <= 12) {
         guitarStrings[pitch + 24].pluck();
      }
   }

   // Purpose: Checks if the character has a string.
   // Pre: No pre conditions
   // Post: Returns if the character has a string.
   public boolean hasString(char key) {
      return (KEYBOARD.indexOf(key) > -1);
   }

   // Purpose: Plays a string based on the key.
   // Pre: The key must be within the size of the keyboard.
   // if condtion is violated, (throws an IllegalArgumentException).
   // Post: Plays the string corolated to the key.
   public void pluck(char key) {
      if (KEYBOARD.indexOf(key) == -1) {
         throw new IllegalArgumentException();
      }
      guitarStrings[KEYBOARD.indexOf(key)].pluck();
   }

   // Purpose: Calculates and returns the sum of all the first frequency of
   // all 37 strings.
   // Pre: No pre conditons.
   // Post: Returns the total of all of the first frequency of all 37 strings.
   public double sample() {
      double sampleSum = 0.0;
      for (GuitarString p: guitarStrings) {
         sampleSum += p.sample();
      }
      return sampleSum;
   }

   // Purpose: Preforms the Karplus-Strong algorithm on all 37 strings.
   // Pre: No pre condtions.
   // Post: Calculates the Karplus-Strong algorithm of all 37 strings.
   public void tic() {
      for (GuitarString p: guitarStrings) {
         p.tic();
      }
      ticCalled++;
   }

   // Purpose: Tracks the amount of time tic is called.
   // Pre: No pre condition.
   // Post: returns the total amout of tic calls
   public int time() {
      return ticCalled;
   }
}