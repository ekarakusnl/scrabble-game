package com.gamecity.scrabble.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <code>Board</code> represents a scrabble board in the platform. A <code>board</code> could have
 * at least two or more {@link User}.
 * 
 * @author ekarakus
 *
 */
@Entity
@Table(name = "boards")
public class Board extends AbstractBaseEntity {

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "player_count", nullable = false)
    private int playerCount;

    @Column(nullable = false)
    private int duration;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "terminate_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date terminateDate;

    @Column(nullable = false)
    private BoardStatus status = BoardStatus.PENDING;

    @Column(name = "turn", nullable = false)
    private int turn = 0;

    /**
     * Returns the userId of the owner
     * 
     * @return userId of the owner of the board
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the ownerId of the board
     * 
     * @param ownerId
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Returns the name of the board
     * 
     * @return name of the board
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the board
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns max number of players allowed to be on the board
     * 
     * @return number of players
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Sets the max number of players allowed to be on the board
     * 
     * @param playerCount
     */
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    /**
     * Gets the max duration for a player move
     * 
     * @return duration in minutes
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of a move in minutes on a board
     * 
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the start date of the game
     * 
     * @return start date as timestamp
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the game
     * 
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the game
     * 
     * @return end date as timestamp
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the game
     * 
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the terminate date of the game
     * 
     * @return terminate date as timestamp
     */
    public Date getTerminateDate() {
        return terminateDate;
    }

    /**
     * Sets the terminate date of the game
     * 
     * @param terminateDate
     */
    public void setTerminateDate(Date terminateDate) {
        this.terminateDate = terminateDate;
    }

    /**
     * Gets the current status of the game
     * 
     * @return status of the game
     */
    public BoardStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of the game
     * 
     * @param status
     */
    public void setStatus(BoardStatus status) {
        this.status = status;
    }

    /**
     * Gets the turn number
     * 
     * @return turn number
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Sets the turn number
     * 
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

}