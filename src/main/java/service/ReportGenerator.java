
package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {

    public static void generate(Path logFolder, Path reportFile)
            throws IOException {

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        StringBuilder report = new StringBuilder();

        if (Files.exists(logFolder)) {
            try (DirectoryStream<Path> files =
                         Files.newDirectoryStream(logFolder)) {

                for (Path file : files) {
                    if (Files.isRegularFile(file)) {

                        try (BufferedReader reader =
                                     Files.newBufferedReader(file)) {

                            String line;

                            while ((line = reader.readLine()) != null) {
                                String lower = line.toLowerCase();

                                try {
                                    String[] parts = line.split(",");
                                    long amount = Long.parseLong(
                                            parts[parts.length - 1].trim());

                                    if (lower.contains("deposit")) {
                                        totalDeposits += amount;
                                    } else if (lower.contains("withdraw")) {
                                        totalWithdrawals += amount;
                                    }
                                } catch (NumberFormatException |
                                         ArrayIndexOutOfBoundsException e) {
                                    // Skip invalid log entries
                                }
                            }
                        }

                        BasicFileAttributes attributes =
                                Files.readAttributes(
                                        file, BasicFileAttributes.class);

                        report.append("File: ")
                                .append(file.getFileName())
                                .append("\nSize: ")
                                .append(attributes.size())
                                .append(" bytes\nLast Modified: ")
                                .append(attributes.lastModifiedTime())
                                .append("\n\n");
                    }
                }
            }
        }

        report.append("Total Deposits: ").append(totalDeposits)
                .append("\nTotal Withdrawals: ").append(totalWithdrawals)
                .append("\n");

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             reportFile,
                             StandardOpenOption.CREATE,
                             StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(report.toString());
        }
    }
}
