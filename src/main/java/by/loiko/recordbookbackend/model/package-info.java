@org.hibernate.annotations.GenericGenerator(
        name = StringConstants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "COMMON_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "100"
                )
        }
)
package by.loiko.recordbookbackend.model;

import by.loiko.recordbookbackend.StringConstants;
