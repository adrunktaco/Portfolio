/*Michael Cerros 
N01491428
4/30/23*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
typedef struct song //don't touch this!
{
int duration;
char* songName;
char* artist;
struct song *next;
}Song;
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////Functions student must complete/////////
void AddNewSong(Song* head); /////
void PrintAllSongs(Song* head); /////
void GetLongestSong(Song* head); /////
void GetTotalDuration(Song* head); /////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////Functions that are already done/////////
/////////////////////////////////////////////////
void DeconstructList(Song* head); /////
Song* ConstructSong(); /////
int main(); /////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
/////////////////////////////////////////////////
//this function uses the ConstructSong() function
//to receive a pointer to a new node, then inserts
//the node at the very end of the existing list.
void AddNewSong(Song* head)
{
Song* newsong=ConstructSong();
Song* nextTmp = NULL;
Song *curr = NULL;
  
  curr = head;
  
      while (curr->next!= NULL) {
          curr = curr ->next;
     }
  
  curr ->next=newsong;
  newsong ->next=nextTmp;
  
   
}
//this function traverses the linked list
//and prints all song information inside the list.
//song duration is printed to the minutes and seconds.
void PrintAllSongs(Song* head)
{
Song *current=NULL;
current=head;
  while (current != NULL) {
  int sec=0;
    int min=0;
min=current->duration/60;
int leftover=min*60;
sec=current->duration-leftover;
   
    
    printf("%s: '%s' %dm %ds  ",current->artist,current->songName,min,sec);
    printf("\n");
    current=current->next;
  }
  
}
//this function traverses the linked list
//and determines which song has the greatest duration.
//it will print out the song name and the duration to minutes and seconds.
void GetLongestSong(Song* head)
{
Song *current = NULL;
Song *longer = NULL;
      current = head;
      longer = current;
  while (current != NULL) {
    
   int mlong = current->duration;
    
   if(mlong > longer->duration){
       longer=current;
     
  }
    
current=current->next;
  }

  
  int duration = longer->duration;
  int min = duration / 60;
  int sec = duration % 60;

  printf("The longest song is '%s' with a duration of %dm %ds",longer->songName,min,sec);
}
//this function traverses the linked list to calculate
//the total duration of the list. It will print
//the duration to the hours, minutes, and seconds.
void GetTotalDuration(Song* head)
{
Song *current=NULL;
int total=0;
    current=head;
        while (current != NULL) {
          total+=current->duration;
          current=current->next;
  }
int hour=0;
int sec=0;
int min=0;
      hour = total / 3600;
      min = (total % 3600) / 60;
      sec = total % 60;
  printf("The total playlist time is %dh %dm %ds",hour,min,sec);
 
}
///////////////////////////////////////////////////
//////////Functions below are already done/////////
///////////////////////////////////////////////////
Song* ConstructSong()
{
Song* newSong = (Song*) calloc(1, sizeof(Song));
char artistName[100];
char songName[100];
int duration;
printf("What is the song name?\n");
fgets(songName, 100, stdin);
songName[strlen(songName)-1] = '\0';
printf("Who is the artist?\n");
fgets(artistName, 100, stdin);
artistName[strlen(artistName)-1] = '\0';
printf("How long is the song?\n");
scanf("%d", &duration);
char* newArtistName = (char*) calloc(strlen(artistName) + 1, sizeof(char));
char* newSongName = (char*) calloc(strlen(songName) + 1, sizeof(char));
strcpy(newArtistName, artistName);
strcpy(newSongName, songName);
newSong->songName = newSongName;
newSong->artist = newArtistName;
newSong->duration = duration;
newSong->next = NULL;
return newSong;
}
void DeconstructList(Song* head)
{
Song* curr = head;
while(curr != NULL)
{
printf("Freeing %s\n", curr->songName);
Song* next = curr->next;
free(curr->artist);
free(curr->songName);
free(curr);
curr = next;
}
}
int main()
{
printf("Welcome to the playlist tracking tool!\n");
printf("Let's handle the first song in the list.\n");
Song* head = ConstructSong();
while(1)
{
printf("\n\nWhat operation do you want to do?\n");
printf("1 = Add New Song\n");
printf("2 = Print All Songs\n");
printf("3 = Get Longest Song\n");
printf("4 = Get Total Playlist Duration\n");
printf("-1 = Quit\n");
int userNum;
scanf("%d", &userNum);
printf("\n\n");
if(userNum == -1)
break;
switch(userNum)
{
case 1:
getchar();
AddNewSong(head);
break;
case 2:
PrintAllSongs(head);
break;
case 3:
GetLongestSong(head);
break;
case 4:
GetTotalDuration(head);
break;
default:
printf("Invalid input\n");
}
}
DeconstructList(head);
}