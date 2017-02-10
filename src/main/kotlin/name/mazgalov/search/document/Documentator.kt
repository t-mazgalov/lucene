package name.mazgalov.search.document

import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.lucene.document.TextField

/**
 * Created on 10-Feb-17.
 * @author <a href="mailto:todor@mazgalov.name">Todor Mazgalov</a>
 */
class Documentator {
    companion object {
        fun createDocument(fields: List<DocumentField>): Document {
            val document: Document = Document()
            for ((fieldName, fieldValue) in fields) {
                document.add(TextField(fieldName, fieldValue, Field.Store.YES))
            }
            return document
        }
    }
}