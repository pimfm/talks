package fm.pim.storage

import fm.pim.tax.nl.NLTaxCalculator
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.profileRoutes() {
    route("/profile") {
        post {
            val profile = call.receive<UserProfile>()
            val saved = ProfileStore.save(profile)
            call.respond(HttpStatusCode.Created, saved)
        }

        get("/{id}") {
            val id = call.parameters["id"]!!
            val profile = ProfileStore.get(id)
                ?: return@get call.respond(HttpStatusCode.NotFound, "Profile not found")
            call.respond(profile)
        }

        post("/{id}/aangifte") {
            val id = call.parameters["id"]!!
            val profile = ProfileStore.get(id)
                ?: return@post call.respond(HttpStatusCode.NotFound, "Profile not found")
            val input = profile.defaultInput
                ?: return@post call.respond(HttpStatusCode.BadRequest, "No default input for profile")
            val report = NLTaxCalculator.calculate(input)
            call.respond(report)
        }
    }
}
