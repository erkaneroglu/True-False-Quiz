package com.example.truefalse.Questions;

import com.example.truefalse.R;

public class Question {

    public String questions[] = {
            "Question 1 Question 1 Question 1 Question 1 Question 1 ",
            "Question 2 Question 2 Question 2 Question 2 Question 2 ",
            "Question 3 Question 3 Question 3 Question 3 Question 3 ",
            "Question 4 Question 4 Question 4 Question 4 Question 4 ",
            "Question 5 Question 5 Question 5 Question 5 Question 5 ",
            "Question 1 Question 1 Question 1 Question 1 Question 1 ",
            "Question 2 Question 2 Question 2 Question 2 Question 2 ",
            "Question 3 Question 3 Question 3 Question 3 Question 3 ",
            "Question 4 Question 4 Question 4 Question 4 Question 4 ",
            "Question 5 Question 5 Question 5 Question 5 Question 5 ",
            "Question 1 Question 1 Question 1 Question 1 Question 1 ",
            "Question 2 Question 2 Question 2 Question 2 Question 2 ",
            "Question 3 Question 3 Question 3 Question 3 Question 3 ",
            "Question 4 Question 4 Question 4 Question 4 Question 4 ",
            "Question 5 Question 5 Question 5 Question 5 Question 5 ",
            "Question 1 Question 1 Question 1 Question 1 Question 1 ",
            "Question 2 Question 2 Question 2 Question 2 Question 2 ",
            "Question 3 Question 3 Question 3 Question 3 Question 3 ",
            "Question 4 Question 4 Question 4 Question 4 Question 4 ",
            "Question 5 Question 5 Question 5 Question 5 Question 5 "
    };

    public String answers[] = {
            "true", //question1 answer
            "false", //question2 answer
            "false", //question3 answer
            "true", //question4 answer
            "true", //question5 answer
            "true", //question1 answer
            "false", //question2 answer
            "false", //question3 answer
            "true", //question4 answer
            "true", //question5 answer
            "true", //question1 answer
            "false", //question2 answer
            "false", //question3 answer
            "true", //question4 answer
            "true", //question5 answer
            "true", //question1 answer
            "false", //question2 answer
            "false", //question3 answer
            "true", //question4 answer
            "true" //question5 answer

    };

    public String hints[] = {
            "Question 1 Hint",
            "Question 2 Hint",
            "Question 3 Hint",
            "Question 4 Hint",
            "Question 5 Hint",
            "Question 1 Hint",
            "Question 2 Hint",
            "Question 3 Hint",
            "Question 4 Hint",
            "Question 5 Hint",
            "Question 1 Hint",
            "Question 2 Hint",
            "Question 3 Hint",
            "Question 4 Hint",
            "Question 5 Hint",
            "Question 1 Hint",
            "Question 2 Hint",
            "Question 3 Hint",
            "Question 4 Hint",
            "Question 5 Hint"
    };

    public int images[] = {
            R.drawable.dog, //question1 photo
            R.drawable.cat, //question2 photo
            R.drawable.chicken, //question3 photo
            R.drawable.owl, //question4 photo
            R.drawable.panda, //question5 photo
            R.drawable.dog, //question1 photo
            R.drawable.cat, //question2 photo
            R.drawable.chicken, //question3 photo
            R.drawable.owl, //question4 photo
            R.drawable.panda, //question5 photo
            R.drawable.dog, //question1 photo
            R.drawable.cat, //question2 photo
            R.drawable.chicken, //question3 photo
            R.drawable.owl, //question4 photo
            R.drawable.panda, //question5 photo
            R.drawable.dog, //question1 photo
            R.drawable.cat, //question2 photo
            R.drawable.chicken, //question3 photo
            R.drawable.owl, //question4 photo
            R.drawable.panda //question5 photo


    };


    public String getQuestionList(int number) {
        return questions[number];
    }

    public String getAnswerList(int number) {
        return answers[number];
    }

    public String getHintList(int number) {
        return hints[number];
    }

    public int getImageList(int number) {
        return images[number];
    }

}
