package name.mazgalov.search.searcher

import name.mazgalov.search.searcher.internal.SearcherImpl
import org.apache.lucene.document.Document
import org.apache.lucene.search.Query
import java.nio.file.Path

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
interface Searcher {
    fun execute(query: Query): MutableList<Document>

    companion object Factory {
        fun create(indexPath: Path): Searcher = SearcherImpl(indexPath)
    }
}