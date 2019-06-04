package com.think.lightningtalk.entity;

import static com.think.lightningtalk.domain.Submission.MAX_DESCRIPTION_LENGTH;
import static com.think.lightningtalk.domain.Submission.MAX_TOPIC_LENGTH;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "submission")
public class SubmissionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
	
	@NotNull
    @Size(max = MAX_TOPIC_LENGTH)
    @Column(name = "topic", length = MAX_TOPIC_LENGTH, nullable = false, unique = true)
    private String topic;

    @NotNull
    @Size(max = MAX_DESCRIPTION_LENGTH)
    @Column(name = "description", length = MAX_DESCRIPTION_LENGTH, nullable = false)
    private String description;
    
    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;
    
    @Column(name = "ipaddress")
    private String ipaddress;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "osname")
    private String osname;

    @Column(name = "useragent")
    private String useragent;

    @ManyToOne(optional = false)
    @NotNull
    private UserEntity user;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Instant getDate() {
        return date;
    }
    
    public void setDate(Instant date) {
        this.date = date;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getOsname() {
        return osname;
    }

    public void setOsname(String osname) {
        this.osname = osname;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubmissionEntity other = (SubmissionEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
