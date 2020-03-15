package com.fiberhome.alg.model;

import java.util.Objects;

public class Relationship {
    private int startNodeId;
    private int endNodeId;
    private String startMKey;
    private String endMKey;
    private String startMValue;
    private String endMValue;
    private Direction direction;
    private String relType;
    private String uniqueValue;
    private long id;

    public long getId() {
        return id;
    }

    public Relationship(int startNodeId, int endNodeId, String startMKey, String endMKey, String startMValue,
                        String endMValue, Direction direction, String uniqueValue, String relType, long id) {
        this.startNodeId = startNodeId;
        this.endNodeId = endNodeId;
        this.startMKey = startMKey;
        this.endMKey = endMKey;
        this.startMValue = startMValue;
        this.endMValue = endMValue;
        this.direction = direction;
        this.uniqueValue = uniqueValue;
        this.relType = relType;
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[").append(startMKey).append(":").append(startMValue).append("]");
        if (direction == Direction.OUTGOING) {
            builder.append("----");
        }
        else {
            builder.append("<----");
        }
        builder.append(relType).append(":").append(uniqueValue);
        if (direction == Direction.OUTGOING) {
            builder.append("---->");
        }
        else {
            builder.append("----");
        }
        builder.append("[").append(endMKey).append(":").append(endMValue).append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Relationship)) return false;
        Relationship that = (Relationship) o;
        return getStartNodeId() == that.getStartNodeId() &&
                getEndNodeId() == that.getEndNodeId() &&
                Objects.equals(getStartMKey(), that.getStartMKey()) &&
                Objects.equals(getEndMKey(), that.getEndMKey()) &&
                Objects.equals(getStartMValue(), that.getStartMValue()) &&
                Objects.equals(getEndMValue(), that.getEndMValue()) &&
                getDirection() == that.getDirection() &&
                Objects.equals(getUniqueValue(), that.getUniqueValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartNodeId(), getEndNodeId(), getStartMKey(), getEndMKey(), getStartMValue(), getEndMValue(), getDirection(), getUniqueValue());
    }

    public int getStartNodeId() {
        return startNodeId;
    }

    public int getEndNodeId() {
        return endNodeId;
    }

    public String getStartMKey() {
        return startMKey;
    }

    public String getEndMKey() {
        return endMKey;
    }

    public String getStartMValue() {
        return startMValue;
    }

    public String getEndMValue() {
        return endMValue;
    }

    public Direction getDirection() {
        return direction;
    }

    public String getUniqueValue() {
        return uniqueValue;
    }

    public String getRelType() {
        return relType;
    }
}
