package data.csvHandler

import java.io.File
import java.nio.file.Path


fun createTestFile(tempDir: Path): File {
    val file = tempDir.resolve("fake_file").toFile()
    return file
}
