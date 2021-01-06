import me.heizi.jsp.shopShit.dao.PersistenceManager
import me.heizi.jsp.shopShit.dao.entities.Product
import org.apache.ibatis.io.Resources
import org.junit.jupiter.api.Test

fun main() {
}

class Resources {
    @Test
    fun getTest(){
        Resources.getResourceAsFile("test.txt").readText().let(::println)
    }
}
class JPA {
    @Test
    fun select() {
        PersistenceManager.useWithResult {
            createQuery("select  p from product as p",Product::class.java)
        }.resultList.size.let(::println)
    }
}

