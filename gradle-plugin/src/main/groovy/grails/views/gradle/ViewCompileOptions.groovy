package grails.views.gradle

import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import org.gradle.api.tasks.compile.GroovyForkOptions
import jakarta.inject.Inject;

/**
 * @author Graeme Rocher
 * @since 1.0
 */
class ViewCompileOptions implements Serializable {

    private static final long serialVersionUID = 0L;

    @Input
    String encoding = "UTF-8"

    @Inject
    protected ObjectFactory getObjectFactory() {
        throw new UnsupportedOperationException();
    }

    @Nested
    GroovyForkOptions forkOptions = getObjectFactory().newInstance(GroovyForkOptions.class)

}
