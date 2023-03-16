import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Ivan Martinez-Kay
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber top = model.top();
        NaturalNumber bottom = model.bottom();

        view.updateTopDisplay(top);
        view.updateBottomDisplay(bottom);

        view.updateSubtractAllowed(top.compareTo(bottom) >= 0);
        view.updateDivideAllowed(!bottom.isZero());
        view.updateRootAllowed(
                bottom.compareTo(TWO) >= 0 && bottom.compareTo(INT_LIMIT) <= 0);
        view.updatePowerAllowed(bottom.compareTo(TWO) >= 0);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        this.model.top().copyFrom(this.model.bottom());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddEvent() {

        //adds top to bottom
        //gives sum in bottom panel

        this.model.bottom().add(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        //subtracts top from bottom
        //gives difference in bottom panel

        this.model.top().subtract(this.model.bottom());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        //multiplies top to bottom
        //gives product in bottom panel

        this.model.bottom().multiply(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {

        //store remainder and move it to top
        //display result at bottom

        NaturalNumber remainder = this.model.top().divide(this.model.bottom());
        this.model.bottom().transferFrom(this.model.top());
        this.model.top().transferFrom(remainder);
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        //finds bottom power of top number
        //clears top and gives result at bottom

        this.model.top().power(this.model.bottom().toInt());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        //finds bottom root of top number
        //clears top and gives result at bottom

        this.model.top().root(this.model.bottom().toInt());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        //allows for multi-digit numbers

        this.model.bottom().multiplyBy10(digit);
        updateViewToMatchModel(this.model, this.view);

    }

}
