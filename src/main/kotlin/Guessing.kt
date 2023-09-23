import java.util.*
import kotlin.random.Random
/**
 * Тут, короче, наскоряк накиданная клёвая игра в угадайку.
 * Оно число загадывает, а пользователь потом угадывает.
 * Есть подсказки, на сколько далеко.
 * todo: DRY, KISS, SOLID (точно можно на ООП-шить), и опечатки по мелочи
 */
object Guessing
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        println("Привет!\nБудешь угадывать? (да/нет)")
        var sc = Scanner(System.`in`)
        var answer = sc.nextLine()
        if (answer == "нет")
        {
            println("(×﹏×)")
            return
        } else if (answer != "да")
        {
            println("(︶︹︺)\n непонятно, давай до свидания")
            return
        }
        println("(⌒‿⌒)")
        while (true)
        {

            val rand: Int = Random.nextInt(1,10)
            println("угадай число от 1 до 10")
            while (true)
            {
                val number = sc.nextInt()
                if (number == rand)
                {
                    println("╰(▔∀▔)╯")
                    println("Будешь угадывать? (да/нет)")
                    sc = Scanner(System.`in`)
                    answer = sc.nextLine()
                    if (answer == "нет")
                    {
                        println("(¬_¬ )")
                        return
                    } else if (answer != "да")
                    {
                        println("(︶︹︺)\n непонятно. Давай, до свидания!")
                        return
                    }
                    println("(⌒‿⌒)")
                    break
                } else
                {
                    if (number < 1 || number > 10)
                    {
                        println("Читать не умеешь?")
                    } else if (Math.abs(number - rand) > 5)
                    {
                        println("Холодно")
                    } else if (Math.abs(number - rand) > 2)
                    {
                        println("Тепло")
                    } else
                    {
                        println("Жгётся!")
                    }
                }
            }
        }
    }
}