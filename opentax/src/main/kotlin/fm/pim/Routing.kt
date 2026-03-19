package fm.pim

import fm.pim.storage.profileRoutes
import fm.pim.tax.be.beRoutes
import fm.pim.tax.nl.nlRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        nlRoutes()
        beRoutes()
        profileRoutes()
    }
}
