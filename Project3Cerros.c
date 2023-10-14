/*
Michael Cerros 
N01491428
4/26/22
*/
#include <stdio.h>
#include <string.h>
void LogFood(FILE* foodzFile);
void PrintByDay(FILE* foodzFile);
void PrintMostCalories(FILE* foodzFile);
void RestLog(FILE* foodzFile);

int main(void) {
FILE* foodzFile = NULL;
int functionNum = 0;
int loopNum = 1;

  foodzFile = fopen("calories.txt", "w");
  while(loopNum == 1){
    
printf("What Function Would You Like To Use?: \n 1 = Log Food \n 2 = Print by Day \n 3 = Print Food With Most Calories \n 4 = Reset Log \n -1 = Quit \n");
    
  scanf("%d", &functionNum);
    
if(functionNum == 1){
  LogFood(foodzFile);
}
else if(functionNum == 2){
  PrintByDay(foodzFile);
}
else if(functionNum == 3){
  PrintMostCalories(foodzFile);
}
else if(functionNum == 4){
  RestLog(foodzFile);
}
else if(functionNum == -1){
  loopNum = -1;
}
    
  }
  printf("Good Luck On Your Diet!");
  fclose(foodzFile);
  return 0;
}

void LogFood(FILE* foodzFile){
char foodName[100];
int foodDay = 0;
int foodCal = 0;
    foodzFile = fopen("calories.txt", "a");
  
  printf(" What Food Item Did You Eat?\n When Did You Eat the Food Item?(Monday = 1, Sunday = 7)\n How Many Calories Was the Food Item?\n");
  
  scanf("%s %d %d", foodName, &foodDay, &foodCal);
  
 fprintf(foodzFile, "%s %d %d\n",foodName, foodDay, foodCal);

  fclose(foodzFile);
  
}
void PrintByDay(FILE* foodzFile){
int monTotal = 0;
int tuesTotal = 0;
int wedTotal = 0;
int thursTotal = 0;
int friTotal = 0;
int satTotal = 0;
int sunTotal= 0;
char fileLine[100];
char foodTemp[100];
int dayTemp = 0;
int calTemp = 0;
  
  foodzFile = fopen("calories.txt", "r");

  while(fscanf(foodzFile, "%s %d %d", foodTemp, &dayTemp, &calTemp) == 3){
    if(dayTemp == 1){
      monTotal += calTemp;
    }
    else if (dayTemp == 2){
      tuesTotal += calTemp;
    }
    else if (dayTemp == 3){
      wedTotal += calTemp;
    }
    else if (dayTemp == 4){
      thursTotal += calTemp;
    }
    else if (dayTemp == 5){
      friTotal += calTemp;
    }
    else if (dayTemp == 6){
      satTotal += calTemp;
    }
    else if (dayTemp == 7){
      sunTotal += calTemp;
    }
    
  }
printf("Monday: %d Calories\n", monTotal);
printf("Tuesday: %d Calories\n", tuesTotal);
printf("Wednesday: %d Calories\n", wedTotal);
printf("Thursday: %d Calories\n", thursTotal);
printf("Friday: %d Calories\n", friTotal);
printf("Saturday: %d Calories\n", satTotal);
printf("Sunday: %d Calories\n", sunTotal);
    fclose(foodzFile);
  
}
void PrintMostCalories(FILE* foodzFile){
char fileLine[100];
char foodTemp[100];
int dayTemp = 0;
int calTemp = 0;
int calMost = 0;
char foodMost[100];
  
  foodzFile = fopen("calories.txt", "r");

  while(fscanf(foodzFile, "%s %d %d", foodTemp, &dayTemp, &calTemp) == 3){
    if(calMost < calTemp){
      calMost = calTemp;
      strcpy(foodMost, foodTemp);
    }
  }
  printf("Food With Most Cals: %s with %d\n", foodMost, calMost);
   fclose(foodzFile);
}

void RestLog(FILE* foodzFile){
 foodzFile = fopen("calories.txt", "w");
  printf("Log Reset.\n");
   fclose(foodzFile);
}