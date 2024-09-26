import { MapTo } from '@adobe/aem-react-editable-components';
import React from 'react';

// Configuration for the Avitest component
const AvitestConfig = {
    emptyLabel: "Avitest",
    isEmpty: function (props) {
        // Ensure imageUrls is an array before checking its length
        return !props || (!props.text && (!Array.isArray(props.imageUrls) || props.imageUrls.length === 0));
    },
};

// Avitest React component
const Avitest = ({ text, imageUrls = [] }) => {
    // Ensure imageUrls is an array
    const images = Array.isArray(imageUrls) ? imageUrls : [];

    return (
        <>
            {/* Display the text property or a default text if not provided */}
            <h1>{text || "Avdhesh"}</h1>

            {/* Display the images if imageUrls are provided */}
            {images.length > 0 ? (
                images.map((url, index) => (
                    <img
                        key={index}
                        src={url}
                        alt=''
                        style={{ maxWidth: '100%', height: 'auto', marginBottom: '10px' }}
                    />
                ))
            ) : (
                <img
                    src='https://tse4.mm.bing.net/th?id=OIP.h4CuaTOEKuVLNF8C2QeO4AHaFj&pid=Api&P=0&h=180'
                    alt=''
                    style={{ maxWidth: '100%', height: 'auto' }}
                />
            )}
        </>
    );
};

// Export the component, mapping it to the AEM resource type
export default MapTo("aem-sites-react/components/avitest")(Avitest, AvitestConfig);
