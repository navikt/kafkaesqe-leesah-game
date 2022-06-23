package no.nav

import no.nav.db.Database
import no.nav.quizrapid.*
import no.nav.rapid.Assessment
import no.nav.rapid.Question


/**
 * QuizApplication
 *
 * Her skal teamet bygge ut funksjonalitet for å løse oppgavene i leesah-game.
 */
class QuizApplication(private val teamName: String, database: Database? = null): QuizParticipant(teamName) {

    override fun handle(question: Question) {
        logger.log(question)

        when(question.category) {
            "team-registration" -> handleRegisterTeam(question)
            "arithmetic" -> handleArithmetic(question)
            else -> {
                print(question.category)
            }
        }
    }

    private fun handleArithmetic(question: Question) {
        var l = question.question.split(" ")


        var tall1 = l[0].toInt()
        var tall2 = l[2].toInt()

        var result = 0

        when(l[1]) {
            "-" -> result = tall1 - tall2
            "+" -> result = tall1 + tall2
            "*" -> result = tall1 * tall2
            "/" -> result = tall1 / tall2
            else -> {

            }
        }


        answer(question.category, question.messageId, result.toString())
    }


    override fun handle(assessment: Assessment) {
        logger.log(assessment)
    }

    /**
     * Spørsmål handlers
     */

    private fun handleRegisterTeam(question: Question) {
        answer(question.category, question.messageId, teamName)
    }

}