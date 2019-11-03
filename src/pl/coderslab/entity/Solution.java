package pl.coderslab.entity;

public class Solution {

    private Integer id;
    private String created;
    private String updated;
    private String description;
    private Integer exerciseId;
    private Integer usersId;

    public Solution() {
    }

    public Solution(String created, String updated, String description) {
        this.created = created;
        this.updated = updated;
        this.description = description;
    }

    public Solution(Integer id, String created, String updated, String description, Integer exerciseId,
                    Integer usersId) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exerciseId = exerciseId;
        this.usersId = usersId;
    }

    public Solution(Integer exerciseId, Integer usersId) {
        this.exerciseId = exerciseId;
        this.usersId = usersId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Integer exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
}
