const config = {
    databaseURL: "https://rollkuchen-35448.firebaseio.com",
    storageBucket: "rollkuchen-35448.appspot.com"
};
firebase.initializeApp(config);

const database = firebase.database().ref('image_pool');
const imgRef = firebase.storage().ref().child('image_pool');

const gallery = $("#gallery");
gallery.nanogallery2({
    items: [],
    locationHash: true,
    thumbnailWidth: 'auto',
    thumbnailHeight: 300,
    thumbnailLabel: {
        display: false
    },
    thumbnailOpenImage: true,
    viewerToolbar: {
        display: false
    },
    viewerTools: {
        topLeft: 'pageCounter',
        topRight: 'closeButton'
    }
});

const ngy2data = gallery.nanogallery2('data');
const instance = gallery.nanogallery2('instance');

database.once('value', function (snapshot) {
    snapshot.val().forEach(obj => {
        Promise.all([
            imgRef.child(obj.src).getDownloadURL(),
            imgRef.child(obj.srct).getDownloadURL()
        ]).then(values => {
            const ID = ngy2data.items.length + 1;
            const albumID = '0';
            const title = obj.src;
            const desc = '';
            const newItem = NGY2Item.New(instance, title, desc, ID, albumID, 'image', '');

            newItem.thumbSet(values[1], 0, 0); // w,h
            newItem.src = values[0];
            gallery.nanogallery2('refresh');
        });
    });
});