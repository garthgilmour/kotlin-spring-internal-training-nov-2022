package optics.stage1.model

import java.net.URI

data class Repo(val location: URI) {
    override fun toString() = "\t\t\t\t$location"
}
