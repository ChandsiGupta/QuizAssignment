package com.gyming.quizassignment.model;

import java.util.Arrays;
 //Model class
public class Questions
{
    private String question;

    private String answer;

    private String[] options;

    public String getQnumber() {
        return qnumber;
    }

    public void setQnumber(String qnumber) {
        this.qnumber = qnumber;
    }

    private String qnumber;

    public String getQuestion ()
    {
        return question;
    }

    public void setQuestion (String question)
    {
        this.question = question;
    }

    public String getAnswer ()
    {
        return answer;
    }



    public void setAnswer (String answer)
    {
        this.answer = answer;
    }

    public String[] getOptions ()
    {
        return options;
    }

    public void setOptions (String[] options)
    {
        this.options = options;
    }
    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", options=" + Arrays.toString(options) +
                '}';
    }

}
			
	