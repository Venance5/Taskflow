package task;

import java.util.Date;

public class SingleTask implements Itask {
	private String desc;
	private Date dueDate;
	private Priority priority;
	private Integer estimdate;
	private Boolean progress;

	public SingleTask() {}
	public SingleTask(String desc, Date dueDate, Priority priority, Integer estimdate, Boolean progress){
		this.desc = desc;
		this.dueDate = dueDate;
		this.priority = priority;
		this.estimdate = estimdate;
		this.progress = progress;
	}

	@Override
	public String getDescription() {
		return desc;
	}

	public void setDescciprion(String desc) {
		this.desc = desc;
	}

	@Override
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date duaDate) {
		this.dueDate = duaDate;
	}

	@Override
	public Priority getPriority(){
		return priority;
	}

	public void setPriority(Priority priority){
		this.priority = priority;
	}

	@Override
	public Integer getEstimatedDate() {
		return estimdate;
	}

	public void setEstimatedDate(Integer estimdate) {
		this.estimdate = estimdate;
	}

	@Override
	public float getProgress(){
		return (progress == true)? 1 : 0;
	}

	public void setProgress(Boolean progress) {
		this.progress = progress;
	}
	
}
