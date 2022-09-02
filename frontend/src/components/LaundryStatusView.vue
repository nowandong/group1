<template>

    <v-data-table
        :headers="headers"
        :items="laundryStatus"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'LaundryStatusView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "status", value: "status" },
            ],
            laundryStatus : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/laundryStatuses'))

            temp.data._embedded.laundryStatuses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.laundryStatus = temp.data._embedded.laundryStatuses;
        },
        methods: {
        }
    }
</script>

