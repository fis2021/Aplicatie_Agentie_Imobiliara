package org.loose.fis.sre.exceptions;

public class AgentDoesNotExistException extends Exception {

    private String agentSchedule;

    public AgentDoesNotExistException(String agent_book) {
        super(String.format("An agent with the name %s does not exist!", agent_book));
        this.agentSchedule = agent_book;

    }

    public String getAgentSchedule() {
        return agentSchedule;
    }
}
