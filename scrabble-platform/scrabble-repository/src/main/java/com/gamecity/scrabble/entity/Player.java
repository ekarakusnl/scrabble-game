package com.gamecity.scrabble.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A <code>player</code> represents a {@link User} who joins or leaves a {@link Board}.
 * 
 * @author ekarakus
 *
 */
@Entity
@Table(name = "players")
public class Player extends AbstractBaseEntity {

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USER_PLAYER"))
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(nullable = false)
    private PlayerStatus status = PlayerStatus.JOINED;

    @Column(nullable = false)
    private int score = 0;

    @Column(name = "join_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;

    @Column(name = "leave_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveDate;

    @Column(name = "turn")
    private int turn;

    @Column(nullable = false, columnDefinition = "tinyint default 0")
    private boolean active = false;

    /**
     * Gets the board id
     * 
     * @return the board id which the player joined
     */
    public Long getBoardId() {
        return boardId;
    }

    /**
     * Sets the board id which the player joined
     * 
     * @param boardId
     */
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    /**
     * Gets the user
     * 
     * @return the user who joined the board
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who joined the board
     * 
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the player status
     * 
     * @return the player status
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * Sets the player status
     * 
     * @param status
     */
    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    /**
     * Gets the game score
     * 
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the game score
     * 
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets the player's turn number
     * 
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Sets the player's turn number
     * 
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * Gets the join date
     * 
     * @return player's join date
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * Sets the player's join date
     * 
     * @param joinDate
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * Gets the leave date
     * 
     * @return player's leave date
     */
    public Date getLeaveDate() {
        return leaveDate;
    }

    /**
     * Sets the player's leave date
     * 
     * @param leaveDate
     */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * Whether the player still is on board
     * 
     * @return player is on board
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets whether the player still is on board
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
