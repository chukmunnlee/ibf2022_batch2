#/bin/bash
mongoimport -d shows -c tvshows \
	--jsonArray --file ./tv-shows.json \
	--drop
