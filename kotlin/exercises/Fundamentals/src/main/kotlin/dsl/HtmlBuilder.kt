package dsl

fun main() {
    // Uncomment and implement the builder

//    val page = html {
//        body {
//            h1 {
//                style("{ font-weight: bold }")
//                hidden(false)
//
//                +"This is an example of a DSL"
//            }
//            div {
//                p {
//                    hidden(true)
//                    +"This is where we could add some conent."
//                    +"Maybe some more"
//                }
//            }
//        }
//    }
//
//    println(page.dump())

    /*
        This should print

        <html>
            <body>
                <h1 style="{ font-weight: bold }" hidden="false">
                    This is an example of a DSL
                </h1>
                <div>
                    <p hidden="true">
                        This is where we could add some conent.
                        Maybe some more
                    </p>
                </div>
            </body>
        </html>
     */
}
