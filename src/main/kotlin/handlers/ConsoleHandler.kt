package handlers

import structures.UserInput

class ConsoleHandler
{
    fun getUserInputLine(): UserInput
    {
        val input = readln()
        return when(input.lowercase())
        {
            "да"-> UserInput.YES
            "нет"-> UserInput.NO
            else -> UserInput.UNDEFINED
        }

    }

    fun getUserInputNumber(): Int
    {
        val input = readln()
        return try
        {
            input.toInt()
        }
        catch (e: NumberFormatException)
        {
            throw NumberFormatException("Невозможно преобразовать введеное сообщение в число")
        }

    }

    fun sendUserOutputLine(message: String?)
    {
        println(message)
    }
}