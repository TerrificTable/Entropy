package feature

abstract class Feature(var name: String, var description: String, var category: Category) {


    abstract fun impl()




    enum class Category(name: String) {

        C2("C2"),
        CLIENT("Client"),
        SERVER("Server"),
        OTHER("Other")

    }
}
