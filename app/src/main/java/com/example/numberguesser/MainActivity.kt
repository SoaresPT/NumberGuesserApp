package com.example.numberguesser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.numberguesser.ui.theme.NumberGuesserTheme
import androidx.compose.ui.text.input.KeyboardType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuesserTheme {
                NumberGuessingGame()
            }
        }
    }
}

@Composable
fun NumberGuessingGame() {
    val range = 1..10
    val guesser = remember { NumberGuesser(range) }
    var userInput by remember { mutableStateOf("") }
    var guessFeedback by remember { mutableStateOf("Make a Guess between ${range.first} and ${range.last}!") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Number Guesser",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Enter your guess") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val userGuess = userInput.toIntOrNull()
                if (userGuess != null) {
                    val result = guesser.makeGuess(userGuess)
                    guessFeedback = when (result) {
                        GuessResult.HIGH -> "Too High! Try a number between ${range.first} and ${range.last}."
                        GuessResult.LOW -> "Too Low! Try a number between ${range.first} and ${range.last}."
                        GuessResult.HIT -> "Correct! You guessed the number!"
                    }
                } else {
                    guessFeedback = "Please enter a valid number between ${range.first} and ${range.last}."
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Guess")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = guessFeedback,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NumberGuessingGamePreview() {
    NumberGuesserTheme {
        NumberGuessingGame()
    }
}
