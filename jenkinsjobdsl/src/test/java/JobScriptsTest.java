import javaposse.jobdsl.dsl.DslScriptLoader;
import javaposse.jobdsl.dsl.MemoryJobManagement;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class JobScriptsTest {

    @Test
    public void should_compile_scripts() throws IOException, URISyntaxException {
        MemoryJobManagement memoryJobManagement = new MemoryJobManagement();
        DslScriptLoader dslScriptLoader = new DslScriptLoader(memoryJobManagement);

        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource("job_dsl.groovy")).toURI());
        Stream<String> lines = Files.lines(path);
        String scriptText = lines.collect(Collectors.joining());
        lines.close();

        dslScriptLoader.runScript(scriptText);
    }
}
