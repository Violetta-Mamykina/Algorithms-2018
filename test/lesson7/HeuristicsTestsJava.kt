package lesson7

//import kotlin.test.Test
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class HeuristicsTestsJava : AbstractHeuristicsTests() {
    @Test
    @Tag("Impossible")
    fun testFillKnapsackCompareWithGreedyTest() {
        fillKnapsackCompareWithGreedyTest { load, items -> JavaHeuristicsTasks.fillKnapsackHeuristics(load, items) }
    }

    @Test
    @Tag("Impossible")
    fun testFindVoyagingPathHeuristics() {
        findVoyagingPathHeuristics { let { JavaHeuristicsTasks.findVoyagingPathHeuristics(it) } }
    }

}