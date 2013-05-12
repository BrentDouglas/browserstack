package io.machinecode.browserstack;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
@JsonAutoDetect(creatorVisibility = NONE,
        fieldVisibility = NONE,
        getterVisibility = NONE,
        isGetterVisibility = NONE,
        setterVisibility = NONE)
public class Status extends BaseImpl {
    private BigDecimal usedTime;
    private BigDecimal totalAvailableTime;
    private Integer runningWindowsSessions;
    private Integer windowsSessionsLimit;
    private Integer runningMacSessions;
    private Integer macSessionsLimit;

    @JsonProperty
    public BigDecimal getUsedTime() {
        return usedTime;
    }

    public Status setUsedTime(final BigDecimal usedTime) {
        this.usedTime = usedTime;
        return this;
    }

    @JsonProperty
    public BigDecimal getTotalAvailableTime() {
        return totalAvailableTime;
    }

    public Status setTotalAvailableTime(final BigDecimal totalAvailableTime) {
        this.totalAvailableTime = totalAvailableTime;
        return this;
    }

    @JsonProperty
    public Integer getRunningWindowsSessions() {
        return runningWindowsSessions;
    }

    public Status setRunningWindowsSessions(final Integer runningWindowsSessions) {
        this.runningWindowsSessions = runningWindowsSessions;
        return this;
    }

    @JsonProperty
    public Integer getWindowsSessionsLimit() {
        return windowsSessionsLimit;
    }

    public Status setWindowsSessionsLimit(final Integer windowsSessionsLimit) {
        this.windowsSessionsLimit = windowsSessionsLimit;
        return this;
    }

    @JsonProperty
    public Integer getRunningMacSessions() {
        return runningMacSessions;
    }

    public Status setRunningMacSessions(final Integer runningMacSessions) {
        this.runningMacSessions = runningMacSessions;
        return this;
    }

    @JsonProperty
    public Integer getMacSessionsLimit() {
        return macSessionsLimit;
    }

    public Status setMacSessionsLimit(final Integer macSessionsLimit) {
        this.macSessionsLimit = macSessionsLimit;
        return this;
    }
}
