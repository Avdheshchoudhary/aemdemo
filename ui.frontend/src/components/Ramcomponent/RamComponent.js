import { MapTo } from '@adobe/aem-react-editable-components';
import React from 'react';

// Configuration for AEM React Editable Components
const RamComponentConfig = {
    emptyLabel: "RamComponent",
    isEmpty: function (props) {
        return !props.text;
    },
};

const RamComponent = (props) => {
    const { text, value } = props;

    return (
        <>
            <h1>{text || "Default Text"}</h1>
            <p>{value || "Default Value"}</p>
        </>
    );
};

export default MapTo("aem-sites-react/components/ramcomponent")(RamComponent, RamComponentConfig);
