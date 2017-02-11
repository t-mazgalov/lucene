import name.mazgalov.search.SearchConstants
import name.mazgalov.search.document.DocumentField
import name.mazgalov.search.document.Documentator
import name.mazgalov.search.indexer.Indexer
import name.mazgalov.search.searcher.Searcher
import org.apache.lucene.document.Document
import org.apache.lucene.index.Term
import org.apache.lucene.search.WildcardQuery
import org.junit.Test
import java.nio.file.Paths

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
class SearchTest {

    @Test
    fun testSearch() {
        val documentFields1 = listOf(
                DocumentField(SearchConstants.FIELD_NAME, "name1"),
                DocumentField(SearchConstants.FIELD_TYPE, "type1"),
                DocumentField(SearchConstants.FIELD_VERSION, "version1"),
                DocumentField(SearchConstants.FIELD_NAMESPACE, "namespace1")
        )
        val document1: Document = Documentator.createDocument(documentFields1)

        val documentFields2 = listOf(
                DocumentField(SearchConstants.FIELD_NAME, "name2"),
                DocumentField(SearchConstants.FIELD_TYPE, "type2"),
                DocumentField(SearchConstants.FIELD_VERSION, "version2"),
                DocumentField(SearchConstants.FIELD_NAMESPACE, "namespace2")
        )
        val document2: Document = Documentator.createDocument(documentFields2)

        val documentFields3 = listOf(
                DocumentField(SearchConstants.FIELD_NAME, "name3"),
                DocumentField(SearchConstants.FIELD_TYPE, "type3"),
                DocumentField(SearchConstants.FIELD_VERSION, "version3"),
                DocumentField(SearchConstants.FIELD_NAMESPACE, "namespace3")
        )
        val document3: Document = Documentator.createDocument(documentFields3)

        val indexLocation = Paths.get(System.getProperty("index.location"))

        val indexer: Indexer = Indexer.create(indexLocation)
        indexer.openIndex()
        indexer.delete()
        indexer.add(listOf(document1, document2, document3))
        indexer.commit()
        indexer.closeIndex()

        val searcher: Searcher = Searcher.create(indexLocation)
        for(document in searcher.execute(WildcardQuery(Term(SearchConstants.FIELD_NAME, "name*")))) {
            println(document)
        }
    }
}