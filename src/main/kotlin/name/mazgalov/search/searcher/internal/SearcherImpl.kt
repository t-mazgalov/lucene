package name.mazgalov.search.searcher.internal

import name.mazgalov.search.searcher.Searcher
import org.apache.lucene.document.Document
import org.apache.lucene.index.DirectoryReader
import org.apache.lucene.search.IndexSearcher
import org.apache.lucene.search.Query
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import java.nio.file.Path

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
class SearcherImpl(val indexLocation: Path): Searcher {

    override fun execute(query: Query): MutableList<Document> {
        val directory: Directory = FSDirectory.open(indexLocation)
        val indexSearcher: IndexSearcher = IndexSearcher(DirectoryReader.open(directory))
        val documents:MutableList<Document> = mutableListOf()
        for(scoreDoc in indexSearcher.search(query, 100).scoreDocs) {
            documents.add(indexSearcher.doc(scoreDoc.doc))
        }
        return documents
    }
}