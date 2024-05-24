package id.ac.ui.cs.advprog.fashionpediareport.enums;

import lombok.Getter;

@Getter
public enum ReportStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String value;
    private ReportStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (ReportStatus reportStatus : ReportStatus.values()) {
            if (reportStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
