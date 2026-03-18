package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = \"Participants\")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = \"participant_id\")
    private Integer participantId;
    @Column(name = \"name\")
    private String name;
    @Column(name = \"address\")
    private String address;
    @Column(name = \"contact_details\")
    private String contactDetails;

    // Getters and Setters
    public Integer getParticipantId() { return participantId; }
    public void setParticipantId(Integer participantId) { this.participantId = participantId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContactDetails() { return contactDetails; }
    public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
}
