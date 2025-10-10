import java.nio.file.*

def template = '''<!DOCTYPE html>
<html lang="en">
<head>
    <title>Redirecting...</title>
    <meta http-equiv="refresh" content="0; url=%s"/>
</head>
<body>
<p>This page has moved to <a href="%s">%s</a></p>
</body>
</html>
'''

def getTargetPath(String path) {
    path = path.replace('C:\\Users\\james\\Documents\\IdeaProjects\\grails-views\\', '')
    path = path.replace('\\', '/')
    if (path.endsWith('index.html')) {
        return path.substring(0, path.length() - 10)
    }
    return path
}

def domain = 'https://grails.apache.org/docs-legacy-views/'

def root = Paths.get(".").toRealPath()

Files.walk(root)
        .filter { path -> Files.isRegularFile(path) && path.toString().endsWith(".html") }
        .each { path ->
            def targetPath = getTargetPath(path.toString())
            if (targetPath) {
                def targetUrl = "${domain}${targetPath}"
                try {
                    new File(path.toString()).write(String.format(template, targetUrl, targetUrl, targetUrl))
                    println "Updated ${path} to redirect to ${targetUrl}"
                } catch (IOException e) {
                    println "Error writing to ${path}: ${e.message}"
                }
            } else {
                println "Skipped ${path}: Not an HTML file"
            }
        }

println "✅ Done."
