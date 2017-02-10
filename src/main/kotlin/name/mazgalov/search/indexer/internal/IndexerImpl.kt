package name.mazgalov.search.indexer.internal

import name.mazgalov.search.indexer.Indexer
import org.apache.lucene.document.Document
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import java.nio.file.Path
import java.util.*

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
class IndexerImpl(indexLocation: Path): Indexer {
    private var index: Directory = FSDirectory.open(indexLocation)
    private val indexWriter: IndexWriter = IndexWriter(index, IndexWriterConfig())

    override fun add(documents: List<Document>) {
        indexWriter.addDocuments(documents)
    }

    override fun commit() {
        indexWriter.commit()
    }

    override fun delete() {
        indexWriter.deleteAll()
    }

    override fun closeIndex() {
        index.close()
    }
}