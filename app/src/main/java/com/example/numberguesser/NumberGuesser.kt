package com.example.numberguesser

class NumberGuesser(range : IntRange) {
    private val secret = range.random()
    var guesses = mutableListOf<Int>()
        private set

    fun makeGuess(guess: Int): GuessResult {
        guesses.plus(guess)
        return if (guess < secret) {
            GuessResult.LOW
        } else if (guess > secret) {
            GuessResult.HIGH
        } else {
            GuessResult.HIT
        }
    }
}