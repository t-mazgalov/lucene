package name.mazgalov.search.indexer.internal

import name.mazgalov.search.indexer.Indexer
import org.apache.lucene.document.Document
import org.apache.lucene.index.IndexWriter
import org.apache.lucene.index.IndexWriterConfig
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import java.nio.file.Path

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
class IndexerImpl(val indexLocation: Path): Indexer {
    lateinit var index: Directory

    lateinit private var indexWriter: IndexWriter

    override fun openIndex() {
        index = FSDirectory.open(indexLocation)
        indexWriter = IndexWriter(index, IndexWriterConfig())
    }

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