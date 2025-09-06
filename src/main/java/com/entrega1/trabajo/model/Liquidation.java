package com.entrega1.trabajo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Liquidation entity representing a payment summary for a referee over a period.
 * It keeps a reference to the referee, the period (month/year) and the total amount.
 */
@Entity
@Table(name = "liquidation")
public class Liquidation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Reference to referee (many liquidations can belong to one referee)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referee_id", nullable = false)
    private Referee referee;

    // Period that this liquidation covers (we'll store the first day of the period)
    @Column(name = "period_start", nullable = false)
    private LocalDate periodStart;

    @Column(name = "period_end", nullable = false)
    private LocalDate periodEnd;

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public Liquidation() {}

    public Liquidation(Referee referee, LocalDate periodStart, LocalDate periodEnd, Double totalAmount) {
        this.referee = referee;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Liquidation{" +
                "id=" + id +
                ", refereeId=" + (referee != null ? referee.getId() : null) +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }
}
