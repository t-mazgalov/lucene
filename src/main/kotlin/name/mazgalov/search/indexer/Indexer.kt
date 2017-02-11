package name.mazgalov.search.indexer

import name.mazgalov.search.indexer.internal.IndexerImpl
import org.apache.lucene.document.Document
import java.nio.file.Path

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
interface Indexer {
    fun openIndex()
    fun add(documents: List<Document>)
    fun commit()
    fun delete()
    fun closeIndex()

    companion object {
        fun create(indexPath: Path): IndexerImpl = IndexerImpl(indexPath)
    }
}