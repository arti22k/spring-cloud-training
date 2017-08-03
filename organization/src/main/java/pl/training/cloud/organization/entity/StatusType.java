package pl.training.cloud.organization.entity;

import pl.training.cloud.organization.types.EnumWithNumericValue;

/**
 * Created by Artur.Kawalec on 2017-07-27.
 */
public enum StatusType implements EnumWithNumericValue{

    /**
     * success
     */
    SUCCESS(5L),
    /**
     * info
     */
    INFO(0L),
    /**
     * warning
     */
    WARNING(-1L),
    /**
     *
     */
    ERROR(-5L);

    /**
     * numeric value
     */
    private Long numericValue;



    /**
     * Constructor
     *
     * @param numericValue
     */
    StatusType(Long numericValue){
        this.numericValue = numericValue;
    }


    /**
     * numeric value of enum type
     *
     * @return
     */
    @Override
    public Long getNumericValue() {
        return numericValue;
    }
}
