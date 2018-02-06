var human = {
    fio: "Vasya Pupkin",
    age: 27,
    address: {
        streetAddress: "Московское ш., 101, кв.101",
        cit: "Ленинград",
        postalCode: 101101
    },
    phoneNumbers:
        "812 123-1234",

    show: function () {
        document.write("<p>FIO:" + this.fio);
        document.write("<p>Age:" + this.age);
        document.write("<p> Phone:" + this.phoneNumbers);
    },

    changePhone: function (phone) {
        this.phoneNumbers = phone;
    }
};


human.changePhone("+7900707080");
human.show();