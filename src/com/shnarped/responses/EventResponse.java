package com.shnarped.responses;

public class EventResponse {

	
        /*"id": 51780,
        "publisher": {
            "id": 11371,
            "first_name": "Shnarped",
            "last_name": "Central",
            "email": "verified",
            "twitter": null,
            "avatar": null,
            "gender": "",
            "birthdate": null,
            "favourite_team_id": null,
            "favourite_team": null,
            "verified": false,
            "pound_count": {
                "total": 3
            },
            "following": false,
            "push": false,
            "player_id": null,
            "player": null
        },
        "reply_id": null,
        "conversation": false,
        "event_count": 1,
        "featured": false,
        "stream": false,
        "created_at": "2013-07-30T07:01:00Z",
        "updated_at": "2013-07-30T07:01:00Z",
        "recipients": [
            {
                "id": 13990,
                "first_name": "CEO",
                "last_name": "Hdjd",
                "email": "verified",
                "twitter": "pushkin2023",
                "avatar": null,
                "gender": "",
                "birthdate": null,
                "favourite_team_id": null,
                "favourite_team": null,
                "verified": false,
                "pound_count": {
                    "total": 1
                },
                "following": false,
                "push": false,
                "player_id": null,
                "player": null
            }
            
            "player":{

    "id":4564,
    "first_name":"Chris",
    "last_name":"Kushneriuk",
    "birthdate":"1986-12-24",
    "hometown":"Ottawa, ON, CAN",
    "image":"http://cluster.leaguestat.com/download.php?client_code=echl&file_path=img/players/3619.jpg",
    "position":"F",
    "jersey":null,
    "weight":"190 lbs",
    "weight_metric":"86 kg",
    "height":"6' 0\"",
    "height_metric":"182 cm",
    "shoots":"R",
    "cap_hit":null,
    "contract_thru":null,
    "current_team_id":0,
    "current_team":null,
    "questions":0,
    "questions_answered":0

}
        ],
        "type": "news",
        "read": null,
        "replied": null,
        "pound_back_enabled": null,
        "title": "Hey CEO,\nWelcome to Shnarped!",
        "body": "Shnarped connects you to your favorite pro hockey players.  On this tab, you can view pounds you've received.  In the short video below, NHL enforcer Kevin Westgarth explains Shnarped.",
        "links": [
            {
                "image_url": null,
                "image_thumb_url": null,
                "link_type": "video",
                "link_name": "Westgarth explains Shnarped",
                "link_url": "http://www.youtube.com/watch?v=vNk1_Lvh_VU",
                "embed_url": "http://www.youtube.com/embed/vNk1_Lvh_VU",
                "embed": "<iframe title='YouTube video player' width='100%' height='100%' frameborder='0' allowfullscreen src='http://www.youtube.com/embed/vNk1_Lvh_VU'></iframe>"
            }
        ],
        "twitter_message": "Hey CEO,\nWelcome to Shnarped! (via @Shnarped)\nhttp://testing.shnarped.com/events/52542",
        "facebook_message": "Hey CEO,\nWelcome to Shnarped! (via Shnarped)\nhttp://testing.shnarped.com/events/52542"*/
	
	public String id="";
	public PublisherClass publisher;
	public String reply_id = "";
	public String conversation = "";
	public String event_count = "";
	public String featured = "";
	public String stream = "";
	public String created_at = "";
	public String updated_at = "";
	public RecipientsClass[] recipients;
	public String type = "";
	public String read = "";
	public String replied = "";
	public String pound_back_enabled = "";
	public String title = "";
	public String body = "";
	public LinksClass[] links;
	public String twitter_message = "";
	public String facebook_message = "";

	public static class PublisherClass{
		
		public String id = "";
		public String first_name = "";
		public String last_name = "";
		public String email = "";
		public String twitter = "";
		public String avatar = "";
		public String gender = "";
		public String birthdate = "";
		public String favourite_team_id = "";
		public String favourite_team = "";
		public String verified = "";
		public PoundCountClass pound_count;
		public class PoundCountClass{
			public String total = "";
		}
		public String following = "";
		public String push = "";
		public String player_id = "";
		public PlayerClass player;
		
	}
	
	public static class RecipientsClass{
		
		public String id = "";
		public String first_name = "";
		public String last_name = "";
		public String email = "";
		public String twitter = "";
		public String avatar = "";
		public String gender = "";
		public String birthdate = "";
		public String favourite_team_id = "";
		public String favourite_team = "";
		public String verified = "";
		public PoundCountClass pound_count;
		
		public String following = "";
		public String push = "";
		public String player_id = "";
		public PlayerClass player;
		
	}
	public static class PoundCountClass{
		public String total = "";
	}
	public static class PlayerClass{
		public String id="";
		public String first_name="";
		public String last_name="";
		public String birthdate="";
		public String hometown="";
		public String image="";
		public String position="";
		public String jersey="";
		public String weight="";
		public String weight_metric="";
		public String height="";
		public String height_metric="";
		public String shoots="";
		public String cap_hit="";
		public String contract_thru="";
		public String current_team_id="";
		public String current_team="";
		public String questions="";
		public String questions_answered="";
	}
	public static class LinksClass{
		
		public String image_url = "";
		public String image_thumb_url = "";
		public String link_type = "";
		public String link_name = "";
		public String link_url = "";
		public String embed_url = "";
		public String embed = "";
		
	}
}
